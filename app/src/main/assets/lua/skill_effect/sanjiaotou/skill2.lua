--v3
--[[
sanjiaotou
1
0
pos_sequence      = {{0.000000,0.000000,-100.000000,0.000000,0,1,0,1,0.000000}}, 
2
{default,qianjin,0,0,0,3,1.000000}{default,gongji2,1,0,4,27,1.000000}
1
{default,0.000000,0.000000,-250.000000,0.000000,0,1,0,3,0.000000,0,0,0,0}
0

0

0

skillCallFunc     = function(self,battleSkill,battleTable) 
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,3,0},{"gongji2",1.000000,4,27,1}}, 
pos_sequence      = {{0.000000,0.000000,-250.000000,0.000000,0,1,0,3,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {}, 
bloodNum          = 1,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "pos_sequence      = {{0.000000,0.000000,-100.000000,0.000000,0,1,0,1,0.000000}}, ",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
